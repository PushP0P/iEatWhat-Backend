import * as React from 'react';
import { ReactElement } from 'react';

interface DescriptionComponentProps {
	name: string;
	isbn: string;
	type: string;
	updatedOn: number;
	blurb?: string;
}

export const DescriptionComponent = (props: DescriptionComponentProps): ReactElement<HTMLDivElement> => {
	return(
		<div
			className="description-component"
		>
			<h2>Description</h2>
			<ul>
				<li>
					<span>
						<b>Name: </b>
						{props.name}
					</span>
				</li>
				<li>
					<span>
						<b>ISBN: </b>
						{props.isbn}
					</span>
				</li>
				<li>
					<span>
						<b>Type: </b>
						{props.type}
					</span>
				</li>
			</ul>
			<p>
				{props.blurb}
			</p>
		</div>
	);
};
