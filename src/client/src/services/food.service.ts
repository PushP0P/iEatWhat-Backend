import { FoodItem } from '../models/food.model';

const FIXTURE_FOOD_DETAILS: FoodItem = {
	id: 'TestFood',
	isbn: '12432foobar',
	slug: 'test-food-12432foobar',
	topic: 'Test Food',
	lastUpdated: Date.now(),
	imageURL: 'https://static.pexels.com/photos/461198/pexels-photo-461198.jpeg',
	foodName: 'Demo Food for Thought',
	categoryTags: ['No Gluten', 'No Dairy', 'Protein'],
	description: {
		name: 'Test Food Yum',
		foodGroup: 'Test Food',
		blurb: `Lorem Ipsum a product sold at no retail store but here for 
			display as a demo statement. One time I found 50 bucks on the ground 
			and I was totally stoked.`
	},
	ingredients: [
		{
			id: 'Foo Bar 0',
			name: 'Foo Bar 0',
			nutrientGroup: 'Foo Bar 0'
		},
		{
			id: 'Foo Bar 1',
			name: 'Foo Bar 1',
			nutrientGroup: 'Foo Bar 1'
		},
		{
			id: 'Foo Bar 2',
			name: 'Foo Bar 2',
			nutrientGroup: 'Foo Bar 2'
		}
	],
};

export async function getFoodDetails(foodId: string): Promise<FoodItem> {
	return FIXTURE_FOOD_DETAILS;
}
