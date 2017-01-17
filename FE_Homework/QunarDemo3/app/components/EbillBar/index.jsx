/**
 * @file component EbillBar
 */
import './style.scss';
import React, { PropTypes } from 'react';

const propTypes = {
	panelSelect: PropTypes.string.isRequired,
	onSelectPanel: PropTypes.func.isRequired
};

class EbillBar extends React.Component {
	render() {
		let panelSelect = this.props.panelSelect,
			selectPanel = this.props.onSelectPanel;
		let onSelect = (that, panel) => {
			if (that.className === 'panel-bg') {
				return;
			}
			selectPanel(panel);
		};

		return (
			<div className="ebill-container">
				<a href="#" ref="notes" className={ panelSelect === "notes" ? "panel-bg" : ""} onClick= { () => onSelect(this.refs.notes, 'notes') }>兑换记录</a>
				<a href="#" ref="detail" className={ panelSelect === "detail" ? "panel-bg" : ""} onClick={ () => onSelect(this.refs.detail, 'detail') } >E币明细</a>
				<a href="#" ref="exchange" className={ panelSelect === "exchange" ? "panel-bg" : ""} onClick={ () => onSelect(this.refs.exchange, 'exchange') }>E币兑换</a>
			</div>
		);
	}
}

EbillBar.propTypes = propTypes;

export default EbillBar;
