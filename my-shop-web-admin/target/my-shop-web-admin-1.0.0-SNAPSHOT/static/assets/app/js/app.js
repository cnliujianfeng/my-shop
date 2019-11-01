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
        //判断用户是否选择数据线
        if (_idArray.length === 0) {

            $('#modal-message').html("您还没有选择任何数据，请至少选择一项");
        } else {
            $('#modal-message').html("您确定删除数据吗");
        }
        //点击删除时弹出模态框
        $('#modal-default').modal('show');

        //如果选择了数据线则调用删除方法
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
                            //请求成功后，无论成功还是失败都需要弹出模态框进行提示 所以需要先解绑原来的click事件
                            $("#btnModalOk").unbind("click");
                            //请求成功
                            if (data.status === 200) {
                                //刷新页面
                                $("#btnModalOk").bind("click", function () {
                                    window.location.reload();
                                });


                                //删除失败
                            } else {
                                //确定按钮事件改为隐藏模态框
                                $("#btnModalOk").unbind("click");
                                $("#btnModalOk").bind("click", function () {
                                    $("#modal-default").modal("hide");
                                });


                            }
                            //无论成败 模态框都要显示消息
                            $('#modal-message').html(data.message);
                            $("#modal-default").modal("show");
                        }
                    });
                }, 500)

            }
        }
    };


    /**
     * 初始化Datatables
     */
    var handlerInitDataTables = function (url, columns) {
        var _dataTable = $('#dataTable').DataTable({
            "paging": true,
            "info": true,
            "lengthChange": false,
            "ordering": false,
            "processing": true,
            "searching": false,
            "serverSide": true,
            "deferRender": true,
            "ajax": {
                "url": url,

            },
            "columns": columns,
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            "drawCallback": function (settings) {
                handlerInitCheckbox();
                handlerCheckboxAll();
                handlerCheckBoxInit();
            }
        });

        return _dataTable;
    };

    /**
     * 查看详情
     * @param url
     */
    var handlerShowDetail = function (url) {
        //通过ajax请求html的方式将jsp装入模态框
        $.ajax({
            url: url,
            type: "get",
            dateType: "html",
            success: function (data) {
                $('#modal-detail-body').html(data);
                $('#modal-detail').modal("show");
            }
        });
    };


    return {

        init: function () {
            handlerInitCheckbox();
            handlerCheckboxAll();
            handlerCheckBoxInit();
        },
        /**
         * 批量删除
         * @param url
         */
        deleteMulti: function (url) {
            handlerDeleteMulti(url);
        },
        /**
         * 初始化dataTables
         * @param url
         * @param columns
         * @returns {jQuery}
         */
        initDataTables: function (url, columns) {
            return handlerInitDataTables(url, columns);
        },
        /**
         * 显示详情
         * @param url
         */
        showDetail: function (url) {
            handlerShowDetail(url);
        }

    }
}();


$(document).ready(function () {
    App.init();
});