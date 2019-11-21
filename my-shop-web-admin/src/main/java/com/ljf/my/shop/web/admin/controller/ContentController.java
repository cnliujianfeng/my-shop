package com.ljf.my.shop.web.admin.controller;

import com.ljf.my.shop.commons.dto.BaseResult;
import com.ljf.my.shop.commons.dto.Pageinfo;
import com.ljf.my.shop.domain.TbContent;
import com.ljf.my.shop.domain.TbUser;
import com.ljf.my.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 内容管理
 * ClassNmae:ContentController
 * Package:com.ljf.my.shop.web.admin.controller
 * Description:
 *
 * @DATE:2019/11/12 12:48
 * @Author:
 */
@Controller
@RequestMapping(value = "content")
public class ContentController {

    @Autowired
    private TbContentService tbContentService;

    @ModelAttribute
    public TbContent getTbContent(Long id) {
        TbContent tbContent = null;
        //id不为空则从数据库获取
        if (id != null) {
            tbContent = tbContentService.getById(id);
        } else {
            tbContent = new TbContent();
        }

        return tbContent;
    }

    /**
     * 跳转到内容列表页面
     *
     *
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {

        return "content_list";
    }

    /**
     * 跳转表单页
     *
     * @return
     */
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form() {

        return "content_form";
    }

    /**
     * 保存信息
     *
     * @param tbContent
     * @param redirectAttributes
     * @param model
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String sava(TbContent tbContent, RedirectAttributes redirectAttributes, Model model) {

        BaseResult baseResult = tbContentService.save(tbContent);

        //保存成功
        if (baseResult.getStatus() == 200) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/content/list";

            //保存失败
        } else {
            model.addAttribute("baseResult", baseResult);
            return "content_form";
        }


    }



    /**
     * 删除信息
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public BaseResult delete(String ids) {
        BaseResult baseResult = null;
        if (StringUtils.isNotBlank(ids)) {
            String[] idArray = ids.split(",");
            tbContentService.deleteMulti(idArray);
            baseResult = BaseResult.success("删除数据成功");
        } else {
            baseResult = BaseResult.fail("删除失败");
        }


        return baseResult;
    }

    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public Pageinfo<TbContent> page(HttpServletRequest request, TbContent tbContent) {


        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");


        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);

        Pageinfo<TbContent> pageinfo = tbContentService.page(start, length, draw,tbContent);
        //处理上传的文件名
       /* List<TbContent> tbContents = pageinfo.getData();
        for (TbContent content : tbContents) {
            content.setPic("/static/upload/"+content.getPic());
        }*/

        return pageinfo;
    }

    /**
     * 显示详情
     * @param tbContent
     * @return
     */
    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(TbContent tbContent){

        return "content_detail";
    }

}
