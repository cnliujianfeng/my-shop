package com.ljf.my.shop.web.admin.controller;

import com.ljf.my.shop.domain.TbContentCategory;
import com.ljf.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassNmae:ContentCategoryController
 * Package:com.ljf.my.shop.web.admin.controller
 * Description:
 *
 * @DATE:2019/11/7 18:34
 * @Author:
 */
@Controller
@RequestMapping(value = "content/category")
public class ContentCategoryController {

    @Autowired
    private TbContentCategoryService tbContentCategoryService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        List<TbContentCategory> targetList = new ArrayList<>();
        List<TbContentCategory> sortList = tbContentCategoryService.selectAll();
        sortList(sortList, targetList, 0L);

        model.addAttribute("tbContentCategories", targetList);
        return "content_category_list";
    }

    /**
     * 树形结构
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "tree/data",method = RequestMethod.POST)
    public List<TbContentCategory> treeData(Long id){

        if(id==null){
            id=0L;
        }
        return  tbContentCategoryService.selectByPid(id);
    }

    /**
     * 排序
     *
     * @param sortList   数据源集合
     * @param targetList 排序后集合
     * @param parentId   父节点的Id
     */

    private void sortList(List<TbContentCategory> sortList, List<TbContentCategory> targetList, long parentId) {
        for (TbContentCategory tbContentCategory : sortList) {
            if (tbContentCategory.getParentId().equals(parentId)) {
                targetList.add(tbContentCategory);

                //判断有没有子节点，如果又继续追加

                if (tbContentCategory.isParent()) {
                    for (TbContentCategory contentCategory : sortList) {
                        if (contentCategory.getParentId().equals(tbContentCategory.getId())) {
                            sortList(sortList, targetList, tbContentCategory.getId());
                            break;
                        }
                    }
                }
            }
        }
    }


}
