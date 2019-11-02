// document.querySelector('.chat[data-chat=person1]').classList.add('active-chat');
// document.querySelector('.person[data-chat=person1]').classList.add('active');
//
// let friends = {
//         list: document.querySelector('ul.people'),
//         all: document.querySelectorAll('.left .person'),
//         name: ''
//     },
//
//     chat = {
//         container: document.querySelector('.container .right'),
//         current: null,
//         person: null,
//         name: document.querySelector('.container .right .top .name')
//     };
//
//
// friends.all.forEach(function (f) {
//     f.addEventListener('mousedown', function () {
//         f.classList.contains('active') || setActiveChat(f);
//     });
// });
//
// function setActiveChat(f) {
//     friends.list.querySelector('.active').classList.remove('active');
//     f.classList.add('active');
//     chat.current = chat.container.querySelector('.active-chat');
//     chat.person = f.getAttribute('data-chat');
//     chat.current.classList.remove('active-chat');
//     chat.container.querySelector('[data-chat="' + chat.person + '"]').classList.add('active-chat');
//     console.log(chat.person);
//     friends.name = f.querySelector('.name').innerText;
//     chat.name.innerHTML = friends.name;
// }