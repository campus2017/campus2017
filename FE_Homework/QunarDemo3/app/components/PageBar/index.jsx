/**
 * @file component PageBar
 */
import './style.scss';
import React, { PropTypes } from 'react';

const propTypes = {

};

function PageBar() {
	return (
		<div className="page-container">
			<div>
				<a href="#" className="page-homepage-btn">首页</a>
				<span className="count"> 1 </span>
				<a href="#" className="page-terminus-btn">末页</a>
				<span>共<span className="all-counts">1</span>页，跳转到</span>
				<input type="text" />
				<span>页</span>
				<a href="#" className="page-confirm-btn">确定</a>
			</div>
		</div>
	);
}

PageBar.propTypes = propTypes;

export default PageBar;