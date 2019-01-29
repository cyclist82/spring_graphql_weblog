import {Injectable} from '@angular/core';
import {Apollo} from 'apollo-angular';
import {BehaviorSubject} from 'rxjs';
import {Category} from '../models/category.model';
import gql from 'graphql-tag';

const categoriesByActive_Q = gql`
    query categoriesByActive($active: Boolean!) {
        categoriesByActive(active: $active) {
            id
            name
            active
        }
    }
`;

@Injectable({
    providedIn: 'root'
})
export class CategoryService {

    activeCategories$ = new BehaviorSubject<Category[] | []>([]);

    constructor(private apollo: Apollo) {
        this.loadActiveCategories();
    }

    loadActiveCategories() {
        this.apollo.query({
            query: categoriesByActive_Q,
            variables: {
                active: true,
            },
        }).subscribe((res) => {
            this.activeCategories$.next(res.data['categoriesByActive']);
        }, (error) => {
            console.log(error);
        });
    }
}
