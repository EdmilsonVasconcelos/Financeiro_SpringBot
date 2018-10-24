package com.mballem.demomvc.domain.jsonviews;

/**
 * 
 * @author vinicius.falcao
 *
 */
public class GenericViews {

	public interface LazyView{}
	public interface DetailedView extends LazyView{}
	
	private GenericViews() {}
	
}
