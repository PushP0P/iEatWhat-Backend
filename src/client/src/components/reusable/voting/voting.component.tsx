import * as React from 'react';
import { ReactElement } from 'react';

export class VotingComponent extends React.Component {

	constructor(public props: any) {
		super(props);
	}

	public render(): ReactElement<HTMLDivElement> {
		return(
			<div
				className="voting-component"
			>
				<h1>Was this useful?</h1>
				<div
					className="control_box"
				>
					<div
						className="col-sm-6"
					>
						<button
							className="btn btn-block btn-success"
						>
							YES
						</button>
					</div>
					<div
						className="col-sm-6"
					>
						<button
							className="btn btn-block btn-warning"
						>
							NO
						</button>
					</div>
				</div>
			</div>
		);
	}
}
