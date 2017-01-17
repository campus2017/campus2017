/**
 * @file componet FlightList
 */
import './style.scss';
import React, { PropTypes } from 'react';

import FlightItem from '../FlightItem';

const propTypes = {
	flightInfos: PropTypes.array
};

function FlightList({ flightInfos }) {
	let infos = flightInfos.map((item, key) => {
		return <FlightItem flightItem={ item } key={ key } />
	});
	return (
		<div className="flight-infos">
			<div className="line"></div>
			<div className="title">
				<li className="flight-desc">航班信息</li>
				<li className="launch-time">当地起飞时间</li>
				<li className="total-time">航行总时长<span className="arrow">◂▸</span></li>
				<li className="land-time">当地降落时间</li>
				<li className="recommend-site">推荐网站</li>
				<li className="low-price">最低报价<span className="arrow">◂▸</span></li>
			</div>
			<ul>
				{ infos }
			</ul>
		</div>
	);
}

FlightList.propTypes = propTypes;

export default FlightList;