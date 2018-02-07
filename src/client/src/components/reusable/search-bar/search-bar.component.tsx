import * as React from 'react';
import { SVGS } from '../../../assets/react-svgs.asset';

export const SearchBarComponent = () => {
	return(
		<div
			className="search-bar-component"
		>
			<input
				type="text"
				className="search-input"
			/>
			<div
				className="cancel-box"
			>
				<div
					className="cancel-icon"
				>
					{SVGS.cross}
				</div>
			</div>
		</div>
	);
};
