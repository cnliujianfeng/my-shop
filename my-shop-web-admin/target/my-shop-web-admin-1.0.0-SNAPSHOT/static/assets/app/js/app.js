var App = function () {

    //iCheck
    var _masterCheckbox = null;
    var _checkbox = null;

    //用于存放ID的数组
    var _idArray;

    /**
     * 私有方法，初始化Icheck
     */
    var handlerInitCheckbox = function () {
        // 激活 iCheck
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass: 'iradio_minimal-blue'
        });


        //获取顶部checkbox
        _masterCheckbox = $('input[type="checkbox"].minimal.icheck_master');

        //获取全部checkbox集合
        _checkbox = $('input[type="checkbox"].minimal');

    };
    /**
     * checkbox全选功能
     */
    var handlerCheckboxAll = function () {
        _masterCheckbox.on("ifClicked", function (e) {
            //返回true表示未选择
            if (e.target.checked) {
                _checkbox.iCheck("uncheck");
            } else {
                _checkbox.iCheck("check");
            }
        });
    };

    /**
     * 分页后页面刷新对于全选框被勾的情况 进行的功能补足
     */
    var handlerCheckBoxInit = function () {

        if (_masterCheckbox.attr('checked')) {
            _checkbox.iCheck("check");
        } else {

            _checkbox.iCheck("uncheck");
        }

    };

    /**
     * 批量删除
     */

    var handlerDeleteMulti = function (url) {
        _idArray = new Array();


        //将选择元素的id放入数组中

        _checkbox.each(function () {
            var _id = $(this).attr('id');

            if (_id != null && _id != 'undefine' && $(this).is(':checked')) {
                _idArray.push(_id);
            }
        });

        if (_idArray.length === 0) {

            $('#modal-message').html("您还没有选择任何数据，请至少选择一项");
        } else {
            $('#modal-message').html("您确定删除数据吗");
        }
        $('#modal-default').modal('show');


        $("#btnModalOk").bind("click", function () {
            del();
        });

        //当前私有函数的私有函数,删除数据
        function del() {
            $("#modal-default").modal("hide");
            //如果没有选择数据则关闭模态框
            if (_idArray.length === 0) {
                //...

                //否则删除
            } else {
                setTimeout(function () {
                    $.ajax({
                        "url": url,
                        "type": "POST",
                        "data": {"ids": _idArray.toString()},
                        "dataType": "JSON",
                        "success": function (data) {
                            if (data.status === 200) {
                                $("#btnModalOk").unbind("click");
                                $("#btnModalOk").bind("click", function () {
                                    window.location.reload();
                                });

                                $('#modal-message').html("删除成功");
                                $("#modal-default").modal("show");


                                //删除失败
                            } else {
                                $("#btnModalOk").unbind("click");
                                $("#btnModalOk").bind("click", function () {
                                    $("#modal-default").modal("hide");
                                });

                                $('#modal-message').html(data.message);
                                $("#modal-default").modal("show");


                            }
                        }
                    });
                }, 500)

            }
        }
    };


    return {

        init: function () {
            handlerInitCheckbox();
            handlerCheckboxAll();
            handlerCheckBoxInit();
        },

        getChechbox: function () {
            return _checkbox;
        },

        deleteMulti: function (url) {
            handlerDeleteMulti(url);
        }

    }
}();


$(document).ready(function () {
    App.init();
});