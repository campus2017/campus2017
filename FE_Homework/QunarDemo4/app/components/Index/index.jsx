/**
 * @file component Index
 */
import './index.scss';
import React from 'react';

import Header from '../common/Header';
import Footer from '../common/Footer';
import PhoneHeader from '../PhoneHeader';
import BgBanner from '../BgBanner';
import BannerList from '../BannerList';
import Mask from '../Mask';
import PhoneDetails from '../PhoneDetails';

import Actions from '../../actions/Actions';
import Stores from '../../stores/Stores';


class Index extends React.Component {
	constructor(props) {
		super(props);
		this.state = Stores.getState();
		this.getDetail = this.getDetail.bind(this);
		this.closeDetail = this.closeDetail.bind(this);
		this.onChange = this.onChange.bind(this);
	}

	componentDidMount() {
		Stores.addChangeListener(this.onChange);
	}

	componentWillMount() {
		Stores.removeChangeListener(this.onChange);
	}

	onChange() {
		console.log()
		this.setState({
			maskShow: Stores.getState().maskShow,
			detailShow: Stores.getState().detailShow
		});
	}

	getDetail() {
		Actions.getDetail({
			maskShow: true,
			detailShow: true
		});
	}

	closeDetail() {
		Actions.closeDetail({
			maskShow: false,
			detailShow: false
		});
	}

	render() {
		return (
			<div className="m-index">
				<Header />
				<PhoneHeader onGetDetail={ this.getDetail } />
				<BgBanner />
				<BannerList />
				<Footer />
				<Mask shouldShow={ this.state.maskShow }/>
				<PhoneDetails onCloseDetail={ this.closeDetail } shouldShow={ this.state.detailShow }/>
			</div>
		);
	}
}

export default Index;