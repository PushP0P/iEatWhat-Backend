import * as React from 'react';
import { ReactElement } from 'react';
import { FoodIngredient } from '../../../models/food.model';

interface IngredientsComponentProps {
	ingredients: FoodIngredient[];
}

export const IngredientsComponent = (props: IngredientsComponentProps): ReactElement<HTMLDivElement> => {
	return(
		<div
			className="ingredients-component"
		>
			<h2>Ingredients</h2>
			<ul>
				{props.ingredients.map((ingredient: FoodIngredient) => {
					return(
						<li
							key={ingredient.id}
						>
							{ingredient.name}
						</li>
					);
				})}
			</ul>
		</div>
	);
};
