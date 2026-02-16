package com.prospire.prospireweb.repository;

import org.apache.ibatis.annotations.Mapper;
import com.prospire.prospireweb.model.Prospect;

@Mapper
/**
 * 見込データ操作用の MyBatis マッパーインタフェース。
 */
public interface ProspectMapper {
    /** 見込テーブルを検索 */
    Prospect findByUsername(Prospect parmProspect);
}