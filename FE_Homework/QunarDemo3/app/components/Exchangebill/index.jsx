/**
 *  @file component Exchangebill
 */
import './style.scss';
import React from 'react';
import EbillBar from '../EbillBar';
import Panel from '../Panel';
import uuid from 'uuid';

let formatDate = () => {
	let date = new Date();
	return `${ date.getFullYear() }-
			${ date.getMonth() + 1 }-
			${ date.getDate() }\n
			${ date.getHours() }:
			${ date.getMinutes() }:
			${ date.getSeconds() }`;
			
};

class Exchangebill extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			items: [{
				id: uuid.v4(),
				time: formatDate(),
				name: "手机50元话费充值",
				count: 1,
				ebill: 190,
				state: false,
				msg: "充值失败，E币已退回。"
			}, {
				id: uuid.v4(),
				time: formatDate(),
				name: "手机50元话费充值",
				count: 1,
				ebill: 190,
				state: false,
				msg: "充值失败，E币已退回。"
			}, {
				id: uuid.v4(),
				time: formatDate(),
				name: "手机50元话费充值",
				count: 1,
				ebill: 190,
				state: false,
				msg: "充值失败，E币已退回。"
			}, {
				id: uuid.v4(),
				time: formatDate(),
				name: "手机50元话费充值",
				count: 1,
				ebill: 190,
				state: false,
				msg: "充值失败，E币已退回。"
			}, {
				id: uuid.v4(),
				time: formatDate(),
				name: "手机50元话费充值",
				count: 1,
				ebill: 190,
				state: false,
				msg: "充值失败，E币已退回。测试用例"
			}, {
				id: uuid.v4(),
				time: formatDate(),
				name: "手机50元话费充值",
				count: 1,
				ebill: 190,
				state: false,
				msg: "充值失败，E币已退回。"
			}, {
				id: uuid.v4(),
				time: formatDate(),
				name: "手机50元话费充值",
				count: 1,
				ebill: 190,
				state: false,
				msg: "充值失败，E币已退回。测试用例"
			}, {
				id: uuid.v4(),
				time: formatDate(),
				name: "博洋梦澜棉被",
				count: 1,
				ebill: 1049,
				state: false,
				msg: "您兑换的商品申请处理失败，金币已退回。测试用例"
			}, {
				id: uuid.v4(),
				time: formatDate(),
				name: "手机50元话费充值",
				count: 1,
				ebill: 190,
				state: false,
				msg: "充值失败，E币已退回。"
			}, {
				id: uuid.v4(),
				time: formatDate(),
				name: "手机50元话费充值",
				count: 1,
				ebill: 190,
				state: false,
				msg: "充值失败，E币已退回。"
			}, {
				id: uuid.v4(),
				time: formatDate(),
				name: "手机50元话费充值",
				count: 1,
				ebill: 190,
				state: false,
				msg: "充值失败，E币已退回。失败处理。"
			}, {
				id: uuid.v4(),
				time: formatDate(),
				name: "米奇真空古堡杯",
				count: 1,
				ebill: 323,
				state: false,
				msg: "您兑换的商品申请处理失败，金币已退回。处理中。失败处理。"
			}, {
				id: uuid.v4(),
				time: formatDate(),
				name: "手机50元话费充值",
				count: 1,
				ebill: 190,
				state: false,
				msg: "充值失败，E币已退回。"
			}],
			curPanel: 'notes',
			curState: 'all'
		};
		this.selectPanel = this.selectPanel.bind(this);
		this.selectState = this.selectState.bind(this);
	}
	
	selectPanel(panel) {
		this.setState({
			curPanel: panel
		});
	}

	selectState(state) {
		this.setState({
			curState: state
		});
	}

	render() {
		return (
			<section className="m-ebill">
				<EbillBar panelSelect={ this.state.curPanel } onSelectPanel={ this.selectPanel } />
				<Panel items={ this.state.items } stateSelect={ this.state.curState } onSelectState={ this.selectState } />
			</section>
		);
	}
}

export default Exchangebill;