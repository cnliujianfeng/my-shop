var App = function () {

    var _masterCheckbox = null;
    var _checkbox = null;

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
                _checkbox.iCheck("uncheck")
            } else {
                _checkbox.iCheck("check")
            }
        });
    };

    return {

        init: function () {
            handlerInitCheckbox();
            handlerCheckboxAll();
        },

        getChechbox:function () {
            return _checkbox;
        }

    }
}();


$(document).ready(function () {
    App.init();
});