import * as React from 'react';
import {
	FOOD_DETAILS_STATE_INIT, FoodDetailsComponentProps,
	FoodDetailsComponentState

} from '../../../models/food.model';
import { ReactElement } from 'react';
import { CommentsComponent } from '../../comments/comments.component';
import { VotingComponent } from '../../reusable/voting/voting.component';
import { getFoodDetails } from '../../../services/food.service';
import { CategoryComponent } from '../../reusable/categories/category.controlled';
import { IngredientsComponent } from './igredients.controlled';
import { DescriptionComponent } from './description.controlled';

export class FoodDetailsComponent extends React.Component<FoodDetailsComponentProps, FoodDetailsComponentState> {

	constructor(public props: FoodDetailsComponentProps) {
		super(props);
		this.state = FOOD_DETAILS_STATE_INIT;
	}

	public async componentDidMount(): Promise<void> {
		const details = await getFoodDetails(this.props.foodId);
		this.setState({
			foodDetails: details,
			dataReady: true
		});
	}

	public render(): ReactElement<HTMLDivElement> {
		console.log('imgURL', this.state.foodDetails.imageURL);
		if (this.state.dataReady) {
			return (
				<div
					className="food-details-component"
				>
					<div
						className="content_wrapper"
					>
						<div
							className="food-details_header"
						>
							<div
								className="header_image_box"
							>
								<img
									src={this.state.foodDetails.imageURL}
									alt={`${this.state.foodDetails.foodName} picture`}
								/>
							</div>
							<h1>{this.state.foodDetails.foodName}</h1>
						</div>
						<div
							className="categories_box"
						>
							{this.state.foodDetails.categoryTags
								.map((tag: string) => {
										return (
											<CategoryComponent
												key={tag}
												tag={tag}
											/>
										);
									}
								)}
						</div>
						<hr />
						<DescriptionComponent
							name={this.state.foodDetails.foodName}
							isbn={this.state.foodDetails.isbn}
							type={this.state.foodDetails.description.foodGroup}
							updatedOn={this.state.foodDetails.lastUpdated}
							blurb={this.state.foodDetails.description.blurb}
						/>
						<hr />
						<IngredientsComponent
							ingredients={this.state.foodDetails.ingredients}
						/>
						<hr />
						<CommentsComponent viewId={this.props.foodId}/>
						<hr />
						<VotingComponent/>
					</div>
				</div>
			);
		} else {
			return (
				<div>
					Loading Data .......
				</div>
			);
		}
	}
}
