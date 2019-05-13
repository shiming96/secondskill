package com.shiming.secondskill.vo;

import com.shiming.secondskill.pojo.Goods;
import lombok.Data;

import java.util.Date;

@Data
public class GoodsVo extends Goods {

    private Double miaoshaPrice;

    private Integer stockCount;

    private Date startDate;

    private Date endDate;
}
