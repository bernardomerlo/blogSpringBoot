
document.addEventListener('DOMContentLoaded', function() {
    // Carregar os posts existentes
    fetch('/posts')
        .then(response => response.json())
        .then(posts => {
            const postList = document.getElementById('postList');
            posts.forEach(post => {
                const listItem = document.createElement('li');
                listItem.innerHTML = `
                    <h2>${post.title}</h2>
                    <p>${post.content}</p>
                    <button onclick="addComment(${post.id})">Adicionar Comentário</button>
                    <ul id="comments${post.id}"></ul>
                `;
                postList.appendChild(listItem);

                // Carregar os comentários existentes para este post
                fetch(`/api/posts/${post.id}/comments`)
                    .then(response => response.json())
                    .then(comments => {
                        const commentsList = document.getElementById(`comments${post.id}`);
                        comments.forEach(comment => {
                            const commentItem = document.createElement('li');
                            commentItem.textContent = comment.content;
                            commentsList.appendChild(commentItem);
                        });
                    });
            });
        });
});

function addComment(postId) {
    const content = prompt('Digite seu comentário:');
    if (content) {
        fetch(`/posts/${postId}/comments`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ content })
        })
        .then(response => response.json())
        .then(comment => {
            const commentsList = document.getElementById(`comments${postId}`);
            const commentItem = document.createElement('li');
            commentItem.textContent = comment.content;
            commentsList.appendChild(commentItem);
        });
    }
}