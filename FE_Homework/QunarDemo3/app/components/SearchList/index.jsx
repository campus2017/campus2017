/**
 * @file component SearchList
 */
import './style.scss';
import React, { PropTypes } from 'react';

const propTypes = {

};

function SearchList() {
	return (
		<div className="search-container">
			<a href="#" className="search-btn">搜索</a>
			<a href="#" className="reset-btn">重置</a>
		</div>
	);
}

SearchList.propTypes = propTypes;

export default SearchList;