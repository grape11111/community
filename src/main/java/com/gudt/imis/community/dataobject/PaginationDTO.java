package com.gudt.imis.community.dataobject;

import com.gudt.imis.community.model.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private int currentPage;
    private int totalPage;
    private List<Integer> pageList;

    public void setPagination(Integer count,Integer size,Integer currentPage){
        pageList=new ArrayList<Integer>();
        if(count%size==0){
            totalPage=count/size;
        }else{
            totalPage=count/size+1;
        }

        if(currentPage<=0){
            currentPage=1;
        }else if(currentPage>totalPage){
            currentPage=totalPage;
        }else {
            this.currentPage = currentPage;
        }


        for(Integer i=currentPage;i<=totalPage;i++,size--){
            if(size!=0){
                pageList.add(i);
            }else{
                break;
            }
        }
    }
}
