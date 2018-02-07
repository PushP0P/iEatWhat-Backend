import * as React from 'react';
import { ReactElement } from 'react';
import { SearchBarComponent } from '../reusable/search-bar/search-bar.component';

/**
 *  FIXTURES - Placeholders
 */
export const NavbarComponent = () => <div>NAVBAR PLACEHOLDER</div>;
export const FooterComponent = () => <div>FOOTER PLACEHOLDER</div>;
export const SearchResultsComponent = () => <div>SEARCH RESULTS PLACEHOLDER</div>;

export class LandingComponent extends React.Component<any, any> {

	constructor(public props: any) {
		super(props);
	}

	public render(): ReactElement<HTMLDivElement> {
		return (
			<div
				className="landing-component container"
			>
				<NavbarComponent />
				<SearchBarComponent />
				<SearchResultsComponent />
				<FooterComponent />
			</div>
		);
	}
}
