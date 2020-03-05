package com.hsn.mall.core.request.create;

import com.hsn.mall.core.model.GoodsAttributeModel;
import com.hsn.mall.core.model.GoodsModel;
import com.hsn.mall.core.model.GoodsProductModel;
import com.hsn.mall.core.model.GoodsSpecificationModel;
import lombok.Data;

import java.io.Serializable;
@Data
public class GoodsAllinone implements Serializable {
    GoodsModel goods;
    GoodsSpecificationModel[] specifications;
    GoodsAttributeModel[] attributes;
    GoodsProductModel[] products;
}
