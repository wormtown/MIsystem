package com.commnow.elasticsearch.dao;

import com.commnow.elasticsearch.bussiness.entity.TbCommnowMedia;

public interface MediaDao {
	
	public void save();
	
	public void delete(Integer[] ids);
	
	public void dynamicUpdate(TbCommnowMedia media);
	
	public TbCommnowMedia findByMediaId(Integer id);
}
