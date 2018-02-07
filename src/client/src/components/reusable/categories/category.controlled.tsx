import * as React from 'react';
import { ReactElement } from 'react';
import { SVGS } from '../../../assets/react-svgs.asset';

interface CategoryComponentProps {
	tag: string;
}

export const CategoryComponent = (props: CategoryComponentProps): ReactElement<HTMLDivElement> => {
	return(
		<div
			className="category-component"
		>

			<div
				className="category_icon"
			>
				<div
					className="icon_wrapper"
				>
					{SVGS.food}
				</div>
			</div>
			<label>
				{props.tag}
			</label>
		</div>
	);
};
