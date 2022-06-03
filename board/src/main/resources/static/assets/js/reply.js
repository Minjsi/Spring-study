/*
reply module
* * */

console.log("reply module.................");
let replyService = (function (){ // 딱 이렇게 박아버리셈 여기서 무조건 이것만 쓰게따
    // js 중괄호는 무조건 객체 키 밸류 써줘야댐
    // json 같은거 ㄴㄴ
    // return {name: "AAAA"};
    function add(reply, callback, error) { // js object 형태로 들어오는 reply // add함수 사용은 html 에서 씀
    $.ajax({ // javascript object 전달하는 것
        url: "/reply/new",
        type: "post",
        data: JSON.stringify(reply),  // 매개변수 // 이 데이터를 contentType에 넣어주는 거심미다
        // 컨트롤러에서는 @RequestBody 값이 vo므로 객체로 보내야 하니 원래는 중괄호 열어야댐  참고로 알아도
        // 원래는 '{"boardBno" : $("input[name='board_bno']").val()}' 이런식으로 써야함 하지만 이걸 해주는 메소드가 있는데...
        // 큰따옴표 붙여주는 stringify 가.. 알아서 해준다 이거임
        // data는 매개변수로 들어오는 값임 매개변수 업스면 안 씀
        contentType: "application/json", // 헤더 타입
        // 내가 보낼 타입은 ajax뭔지 모르니까 알려줘야댐 컨트롤러에 consumes와 동일해야됨
        // 컨트롤러에 consumes가 안 붙어있으면 @RequestBody 이런걸 보면 됨

        // dataType은 언제 쓰냐
        // 컨트롤러에서 리턴타입이 list 타입이면 dataType:json 이라고 쓰면댐

        success : function (result) { // 컨트롤러에서 return한 값 이 result가 댄다. 이 result로 DOM을 쓴다.
            //  위에 type 덕분에 result는 json이 된다.
            // dom은 html에서 부르는쪽에서 쓴다 이게 모듈화다
            // 콜백함수로 마무리 하는게 바로 모듈화
            if(callback) {
                callback(result); // 콜백을 쓰는 이유는 사용한 add 쪽으로 다시 돌려주기 위함
            }
        },
        error : function (xhr, status, er) {
            if(error) {
                error(xhr, status, er); // console에서 확인을 해보면 된다.
            }
        }
    })
    }

    // function add(reply, callback, error){
    //     console.log("add reply..........");
    //     $.ajax({
    //         url: "/reply/new",
    //         type: "post",
    //         data: JSON.stringify(reply),
    //         contentType: "application/json",
    //         success: function(result){
    //             if(callback){
    //                 callback(result);
    //             }
    //         },
    //         error: function(xhr, status, er){
    //             if(error){
    //                 error(xhr, status, er);
    //             }
    //         }
    //     });
    // }


    console.log("readone///////////////////")
    function readone(rno, callback, error) {
        $.ajax({
            url : "/reply/" + rno,
            type : "get",
            // data: JSON.stringify(reply),
            // dataType : JSON,
            dataType : "json",
            // contentType : "application/json",

            success : function (result) {
                if(callback) {
                    callback(result);
                }
            },
            error : function (xhr, status, er) {
                if(error) {
                    error(xhr, status, er); // console에서 확인을 해보면 된다.
                }
            }
        })
    }

    function remove(rno, callback, error) {
        $.ajax({
            url : "/reply/" + rno,
            type : "delete",


            success : function (result) {
                if(callback) {
                    callback(result);
                }
            },
            error : function (xhr, status, er) {
                if(error) {
                    error(xhr, status, er); // console에서 확인을 해보면 된다.
                }
            }
        })
    }

    function modify(rno, reply, callback, error){
            console.log("add reply..........");
            $.ajax({
                url: "/reply/" + rno,
                type: "patch",
                data: JSON.stringify(reply),
                contentType: "application/json",
                success: function(result){
                    if(callback){
                        callback(result);
                    }
                },
                error: function(xhr, status, er){
                    if(error){
                        error(xhr, status, er);
                    }
                }
            });
        }

        function getlist(param, callback, error) {
        $.ajax({
            url : "/reply/list/" + param.bno  + "/" + param.page,
            type : "get",
            dataType : "json",

            success: function(list){
                if(callback){
                    callback(list);
                }
            },
            error: function(xhr, status, er){
                if(error){
                    error(xhr, status, er);
                }
            }
        })
        }

        function one(callback, error) {
        $.ajax({
            url : "/reply/success",
            type : "get",
            success: function(result){
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, er){
                if(error){
                    error(xhr, status, er);
                }
            }
        })
        }
        function two(note, callback, error) {
            $.ajax({
                url : "/reply/two/" + note,
                type : "post",
                success: function(result){
                    if(callback){
                        callback(result);
                    }
                },
                error: function(xhr, status, er){
                    if(error){
                        error(xhr, status, er);
                    }
                }
            })
        }

        function three(callback, error) {
            $.ajax({
                url : "/reply/three",
                type : "get",
                dataType : "json",
                success: function(result){
                    if(callback){
                        callback(result);
                    }
                },
                error: function(xhr, status, er){
                    if(error){
                        error(xhr, status, er);
                    }
                }
            })
        }

        function four(param, reply, callback, error) {
            $.ajax({
                url: "/reply/fruit/" + param.fno + "/" + param.fname + "/" + param.fspecies + "/",
                type : "post",
                data: JSON.stringify(reply),
                dataType: "json",
                contentType: "application/json",
                success: function(result){
                    if(callback){
                        callback(result);
                    }
                },
                error: function(xhr, status, er){
                    if(error){
                        error(xhr, status, er);
                    }
                }
            });
        }

        function five(input, callback, error) {
            $.ajax({
                url : "/reply/five",
                type : "post",
                data : JSON.stringify(input),
                contentType : "application/json",
                dataType : "json",
                success: function(result){
                    if(callback){
                        callback(result);
                    }
                },
                error: function(xhr, status, er){
                    if(error){
                        error(xhr, status, er);
                    }
                }
            })
        }

    return {
        add : add,
        readone : readone,
        remove : remove,
        modify : modify,
        getlist : getlist,
        three : three,
        four : four,
        one : one,
        two : two,
        five : five
    };
})(
    // 사용하는 괄호
 );