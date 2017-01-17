/**
 * @file component ListItem
 */
import './style.scss';
import React, { PropTypes } from 'react';

const propTypes = {
	item: PropTypes.object
};

function ListItem({ item }) {
	return (
		<li className="item-info">
			<span>{ item.time }</span>
			<span>{ item.name }</span>
			<span>{ item.count }</span>
			<span>{ item.ebill }</span>
			<span>{ item.state === false ? "兑换失败" : "兑换成功" }</span>
			<span>{ item.msg }</span>
			<a className="exchange-info-btn" href="#">兑换信息</a>
			<span></span>
		</li>
	);
}

ListItem.propTypes = propTypes;

export default ListItem;
