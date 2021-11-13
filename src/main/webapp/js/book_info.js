$(document).ready(function () {
    let form = $("#add-comment-form")
    let commentsList = $("#comment-list")
    form.on('submit', function () {
        let content = form.find("#content").val();
        if (content === '') {
            return false
        }
        $.ajax("/add-comment", {
            method: "POST",
            data: "content=" + content,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            success: function (data) {
                let commentDto = JSON.parse(data)
                console.log(commentDto)
                form.find("#content").val("")
                let commentTag = $("<div></div>")
                let options = { year: "numeric", month: "long", day: "numeric", hour: "numeric", minute: "numeric" }
                commentTag.append("<div class=\"light_gray text\">" + commentDto.author.firstName + "</div>")
                commentTag.append("<div class=\"light_gray text\">" + new Date(commentDto.createdAt).toLocaleString('ru-RU', options) + "</div>")
                commentTag.append("<div class=\"content\">" + commentDto.content + "</div>")
                commentTag.append("<div class=\"divider\"></div>")
                commentTag.hide()
                commentsList.prepend(commentTag)
                commentTag.show(300)

                let commentsCountSpan = $("#commentsCount")
                commentsCountSpan.html(1 + parseInt(commentsCountSpan.html()))
            }
        })
        return false
    })
})