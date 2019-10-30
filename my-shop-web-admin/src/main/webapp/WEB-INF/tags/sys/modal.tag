<%@tag language="java" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="title" type="java.lang.String" required="false" description="模态框的标题" %>
<%@ attribute name="message" type="java.lang.String" required="true" description="模态框的消息" %>
<%@ attribute name="opts" type="java.lang.String" required="false" description="操作类型:info/信息提示 confirm/确认对话框" %>
<%@ attribute name="url" type="java.lang.String" required="false" description="跳转连接，主要用于确认对话框删除时使用" %>

<div class="modal fade" id="modal-default">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">${title==null?"温馨提示":title}</h4>
            </div>
            <div class="modal-body">
                <p>${message}&hellip;</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">关闭</button>
                <button type="button" id="btnModalOk" class="btn btn-primary">确定</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>


<script>
   $(function () {
       $("#btnModalOk").bind('click', function () {
           <c:if test="${opts!='confirm'}">
           $('#modal-default').modal('hide');
           </c:if>

           <c:if test="${opts=='confirm'}">
           console.log("${url}")
           </c:if>

       });
   });
</script>