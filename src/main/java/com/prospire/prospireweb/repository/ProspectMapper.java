package com.prospire.prospireweb.repository;

import org.apache.ibatis.annotations.Mapper;
import com.prospire.prospireweb.model.Prospect;

@Mapper
public interface ProspectMapper {
    /** 見込テーブルを検索 */
    Prospect findByUsername(Prospect parmProspect);
}