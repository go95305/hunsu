<!doctype html>
<html lang="en">
<head>
    <title>Websocket Chat</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!-- CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/4.3.1/dist/css/bootstrap.min.css">
    <style>
        [v-cloak] {
            display: none;
        }
    </style>
</head>
<body>
<div class="container" id="app" v-cloak>
    <div class="row">
        <div class="col-md-6">
            <h3>채팅방 리스트</h3>
        </div>
        <div class="col-md-6 text-right">
            <a class="btn btn-primary btn-sm" href="/chat/room">방 나가기</a>
            <a class="btn btn-primary btn-sm" @click="findAllRoom(0)">최신순</a>
            <a class="btn btn-primary btn-sm" @click="findAllRoom(1)">인기순</a>
        </div>
    </div>
    <div class="input-group">
        <div class="input-group-prepend">
            <label class="input-group-text">방제목</label>
            <input type="text" class="form-control" v-model="room_name">
        </div>
        <div class="input-group-prepend">
            <label class="input-group-text">해시태그</label>
            <input type="text" class="form-control" v-model="hashtagList">
        </div>
        <div class="input-group-prepend">
            <label class="input-group-text">고정댓글</label>
            <input type="text" class="form-control" v-model="fixedComment">
        </div>
        <div class="input-group-append">
            <button class="btn btn-primary" type="button" @click="createRoom">채팅방 개설</button>
        </div>
    </div>
    <ul class="list-group">
        <li class="list-group-item list-group-item-action" v-for="item in chatrooms" v-bind:key="item.roomId">
            <h6>{{item.name}} <span class="badge badge-info badge-pill">{{item.userCount}}</span><span
                        class="badge badge-info badge-pill">{{item.likeCount}}</span></h6>
            <span class="badge badge-info badge-pill">{{item.hashtagList}}</span>
            <br/>
            <button class="btn btn-primary" type="button" v-on:click="enterRoom(item.roomId, item.name)">방입장</button>
            <button class="btn btn-primary" type="button" @click="removeRoom(item.roomId)">방 삭제</button>
        </li>
    </ul>
</div>
<!-- JavaScript -->
<script src="/webjars/vue/2.5.16/dist/vue.min.js"></script>
<script src="/webjars/axios/0.17.1/dist/axios.min.js"></script>
<script>
    var vm = new Vue({
        el: '#app',
        data: {
            room_name: '',
            publisher: '',
            hashtagList: [],
            chatrooms: [],
            fixedComment: '',
        },
        created() {
            const tmp = 0;
            this.findAllRoom(tmp);
        },
        methods: {
            removeRoom: function (roomId) {
                axios.post('/chat/room/remove/' + roomId)
                alert("방 삭제 완료")
                var tmp = 0;
                this.findAllRoom(tmp);
            },
            findAllRoom: function (num) {
                axios.get('/chat/rooms/' + num).then(response => {
                    // prevent html, allow json array
                    if (Object.prototype.toString.call(response.data) === "[object Array]")
                        console.log(response.data);
                    this.chatrooms = response.data;
                });
            },
            createRoom: function () {
                if ("" === this.room_name) {
                    alert("방 제목을 입력해 주십시요.");
                    return;
                } else {
                    axios.post('/chat/room', {
                        name: this.room_name,
                        publisher: this.publisher,
                        hashtagList: this.hashtagList,
                        fixedComment: this.fixedComment
                    })
                        .then(
                            response => {
                                alert(response.data.name + "방 개설에 성공하였습니다.")
                                this.room_name = '';
                                this.hashtagList = [];
                                this.fixedComment = '';
                                var tmp = 0;
                                this.findAllRoom(tmp);
                            }
                        )
                        .catch(response => {
                            alert("채팅방 개설에 실패하였습니다.");
                        });
                }
            },
            enterRoom: function (roomId, roomName) {
                localStorage.setItem('wschat.roomId', roomId);
                localStorage.setItem('wschat.roomName', roomName);
                location.href = "/chat/room/enter/" + roomId;
            }
        }
    });
</script>
</body>
</html>