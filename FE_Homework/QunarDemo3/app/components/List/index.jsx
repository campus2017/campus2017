/**
 * @file component List
 */
import './style.scss';
import React, { PropTypes } from 'react';
import ListItem from '../ListItem';

const propTypes = {
	items: PropTypes.array.isRequired
};

function List({ items }) {
	if ( items.length === 0) {
		return (
			<div className="no-data">
				貌似没有数据~
			</div>
		);
	}
	//循环插入子组件
	const itemsContent = items.map((item) => {
		return <ListItem item={ item } key={ item.id } />
	});
	return (
		<div className="list-container">
			<div className="caption">
				<span>时间</span>
				<span>商品名称</span>
				<span>数量</span>
				<span>E币</span>
				<span>状态</span>
				<span>信息</span>
				<span>兑换信息</span>
				<span>确认</span>
			</div>
			<ul>
				{ itemsContent }
			</ul>
		</div>
	);
}

List.propTypes = propTypes;

export default List;