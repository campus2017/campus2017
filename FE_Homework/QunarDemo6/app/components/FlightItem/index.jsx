/**
 * @file component FlightItem
 */
import './style.scss';
import React, { PropTypes } from 'react';

const propTypes = {
	flightItem: PropTypes.object
};

function FlightItem({ flightItem }) {
	let sites = flightItem.recommend.map((item, key) => {
		return <li key={ key } className="site-list">
				<span className="site">{ item.site }</span><br />
				<span className="price">￥ { item.price }</span>
			   </li>
	});
	return (
		<li className="flight-item">
			<div className="flight-desc">
				<div className="go">
					<span className="air-icon"></span>{ flightItem.go.name }<br />
					<span className="width">{ flightItem.go.width }</span> <span className={ flightItem.go.isCommon ? 'common' : '' }>共享</span><br />
					<span className="height">{ flightItem.go.height }</span>
				</div>
				<div className="back">
					<span className="air-icon"></span>{ flightItem.back.name }<br />
					<span className="width">{ flightItem.back.width }</span> <span className={ flightItem.back.isCommon ? 'common' : '' }>共享</span><br />
					<span className="height">{ flightItem.back.height }</span>
				</div>
			</div>
			<div className="launch-time">
				<div className="go">
					<span className="time">{ flightItem.go.launchTime }</span>
					<span className="airport">{ flightItem.go.launchAirport }</span>
				</div>
				<div className="back">
					<span className="time">{ flightItem.back.launchTime }</span>
					<span className="airport">{ flightItem.back.launchAirport }</span>
				</div>
			</div>
			<div className="total-time">
				<div className="go">
					<span className="cost-time">约16小时</span>
					<div className="transfer-wrap">
						<span className="transfer">转</span><span> { flightItem.go.transferSite }</span>
					</div>
					<div className="stop-wrap">
						<span className="stop">停</span><span> { flightItem.back.transferAirport }</span>
					</div>
				</div>
				<div className="back">
					<span className="cost-time">约16小时</span>
					<div className="transfer-wrap">
						<span className="transfer">转</span><span> { flightItem.go.transferSite }</span>
					</div>
					<div className="stop-wrap">
						<span className="stop">停</span><span> { flightItem.back.transferAirport }</span>
					</div>
				</div>
			</div>
			<div className="land-time">
				<div className="go">
					<span className="time">{ flightItem.go.landTime }</span>
					<span className="airport">{ flightItem.go.landAirport }</span>
				</div>
				<div className="back">
					<span className="time">{ flightItem.back.landTime }</span>
					<span className="airport">{ flightItem.back.landAirport }</span>
				</div>
			</div>
			<div className="recommend-site">
				<ul>
					{ sites }
				</ul>
			</div>
			<div className="low-price">
				￥<span className="price">{ flightItem.lowPrice }</span>票价<br />
				<div className="tax-wrap">￥<span className="tax">{ flightItem.tax }</span>税费</div>
				<span className="btn-ticket">订票 ▴</span>
				<span className="triangle"></span>
			</div>
		</li>
	);
}

FlightItem.propTypes = propTypes;

export default FlightItem;
