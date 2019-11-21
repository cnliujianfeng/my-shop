var App = function () {

    //iCheck
    var _masterCheckbox = null;
    var _checkbox = null;

    //用于存放ID的数组
    var _idArray;

    //默认的DropZone参数
    var defalutDropzoneOpts = {
        url: "",
        dictDefaultMessage: '拖动文件至此或者点击上传', // 设置默认的提示语句
        paramName: "dropFile", // 传到后台的参数名称
        maxFiles: 1,// 一次性上传的文件数量上限
        maxFilesize: 2, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        //previewsContainer:"#preview", // 上传图片的预览窗口
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传" + this.maxFiles + "个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持.",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消",
    };

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
        //判断用户是否选择数据项
        if (_idArray.length === 0) {

            $('#modal-message').html("您还没有选择任何数据，请至少选择一项");
        } else {
            $('#modal-message').html("您确定删除数据吗");
        }
        //点击删除时弹出模态框
        $('#modal-default').modal('show');

        //如果选择了数据项则调用删除方法
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
     * 初始化Dropzone
     * @param opts
     */
    var handlerInitDropzone = function (opts) {
        Dropzone.autoDiscover = false;
        //继承
        $.extend(defalutDropzoneOpts, opts);


        new Dropzone(defalutDropzoneOpts.id, defalutDropzoneOpts);
    };


    /* {

         init: function () {
             this.on("success", function (file, data) {
                 // 上传成功触发的事件
                 defalutDropzoneOpts.success(file,data);
             });
         }*/

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
    /**
     * 初始化zTree
     * @param url
     * @param autoParam
     * @param callback
     */
    var handlerInitZTee = function (url, autoParam, callback) {
        var setting = {
            view: {
                selectedMulti: false
            },
            async: {
                enable: true,
                url: url,
                autoParam: autoParam


            }
        };


        $.fn.zTree.init($("#myTree"), setting);

        $("#btnModalOk").bind("click", function () {
            var zTree = $.fn.zTree.getZTreeObj("myTree");
            var nodes = zTree.getSelectedNodes();
            //未选择
            if (nodes.length == 0) {
                alert("请先选择一个节点");

                //已选择
            } else {
                callback(nodes);

            }

        });


    };


    return {
        /**
         * 初始化
         */
        init: function () {
            handlerInitCheckbox();
            handlerCheckboxAll();
            handlerCheckBoxInit();
            //关门比DropZone的自动发现功能

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
        },
        /**
         * 初始化zTree
         * @param url
         * @param autoParam
         * @param callback
         */
        initZTree: function (url, autoParam, callback) {
            handlerInitZTee(url, autoParam, callback);
        },
        /**
         * 初始化DropZone
         * @param opts
         */
        initDropzone: function (opts) {
            handlerInitDropzone(opts);
        },

    }
}();


$(document).ready(function () {
    App.init();
});