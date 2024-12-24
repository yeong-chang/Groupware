<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../board/layout/sidebar.jsp" %>

<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>Websocket Chat</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/4.3.1/dist/css/bootstrap.min.css">

    <style>
        body {
            margin: 0;
            background-color: #f8f9fa;
            font-family: 'Arial', sans-serif;
        }

        .container {
            margin-top: 0px;
            display: flex;
            flex-direction: column;
            margin-left: 10%;
        }

        .row {
            margin-bottom: 20px;
        }

        .input-group {
            margin-bottom: 20px;
        }

        .input-group .form-control {
            border-radius: 30px;
            padding: 10px 40px;
            margin-bottom: 20px;
            margin-top: 20px;
        }

        .input-group-prepend .input-group-text {
            background-color: #3182f6;
            color: #fff;
            padding: 10px 30px;
            border-radius: 30px 30px;
        }

        .input-group-append .btn {
            background-color: #3182f6;
            color: #fff;
            border-radius:  30px 30px ;
            padding: 20px 40px;
            transition: background-color 0.3s;
        }

        .input-group-append .btn:hover {
            background-color: #2272eb;
        }

        h3 {
            color: #333;
            font-weight: 600;
        }

        .list-group {
            max-height: 400px;
            overflow-y: auto;
            margin-top: 20px;
        }

        .list-group-item {
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin-bottom: 10px;
            transition: background-color 0.3s, transform 0.2s ease;
        }

        .list-group-item:hover {
            background-color: #e8f3ff;
            transform: scale(1.03);
            cursor: pointer;
        }

        .list-group-item.active {
            background-color: #3182f6;
            color: white;
        }

        [v-cloak] {
            display: none;
        }
    </style>
</head>
<body>
<div class="container" id="app" v-cloak>
    <div class="row">
        <div class="col-md-12">
            <h3>채팅방 리스트</h3>
        </div>
    </div>

    <!-- 채팅방 개설 입력창 -->
    <div class="input-group">
        <div class="input-group-prepend">
            <label class="input-group-text">방제목</label>
        </div>
        <input type="text" class="form-control" v-model="room_name" v-on:keyup.enter="createRoom">
        <div class="input-group-append">
            <button class="btn btn-primary" type="button" @click="createRoom">채팅방 개설</button>
        </div>
    </div>

    <!-- 채팅방 리스트 -->
    <ul class="list-group">
        <li class="list-group-item list-group-item-action" v-for="item in chatrooms" v-bind:key="item.roomId" v-on:click="enterRoom(item.roomId)">
            {{item.name}}
        </li>
    </ul>
</div>

<!-- JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/axios@0.17.1/dist/axios.min.js"></script>

<script>
    var vm = new Vue({
        el: '#app',
        data: {
            room_name : '',
            chatrooms: []
        },
        created() {
            this.findAllRoom();
        },
        methods: {
            findAllRoom: function() {
                axios.get('/chat/rooms.do').then(response => { this.chatrooms = response.data; });
            },
            createRoom: function() {
                if("" === this.room_name) {
                    alert("방 제목을 입력해 주십시요.");
                    return;
                } else {
                    var params = new URLSearchParams();
                    params.append("name", this.room_name);
                    axios.post('/chat/room.do', params)
                        .then(response => {
                            alert(response.data.name + " 방 개설에 성공하였습니다.");
                            this.room_name = '';
                            this.findAllRoom();
                        })
                        .catch(response => { alert("채팅방 개설에 실패하였습니다."); });
                }
            },
            enterRoom: function(roomId) {
                var sender = prompt('대화명을 입력해 주세요.');
                if(sender != "") {
                    localStorage.setItem('wschat.sender', sender);
                    localStorage.setItem('wschat.roomId', roomId);
                    location.href = "/chat/room/enter/" + roomId + ".do";
                }
            }
        }
    });
</script>

</body>
</html>
