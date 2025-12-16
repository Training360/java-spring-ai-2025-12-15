window.onload = function () {

    document.getElementById("question-textarea").addEventListener("keydown",
        function (event) {
            if (event.key === "Enter") {
                event.preventDefault();
                read();
            }
        })

}

function read() {
    const question = document.getElementById("question-textarea").value;

    fetch("/api/stream", {method: "POST", body: question})
        .then(response => subscribe())

}

function subscribe() {
    const answerDiv = document.getElementById("answer-div");
    answerDiv.innerHTML = "";

    const source = new EventSource("/api/stream");
    source.onmessage = function (event) {
        const data = event.data;
        const json = JSON.parse(data);
        const text = json.text;
        const messageType = json.messageType;

        if (messageType === "INTERMEDIATE") {
            answerDiv.innerHTML += text;
        }
        else {
            source.close();

            // Markdown formázás
            answerDiv.innerHTML = marked.parse(answerDiv.textContent)
        }

    }
}

