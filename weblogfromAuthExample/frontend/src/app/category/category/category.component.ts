import {Component, OnInit} from '@angular/core';
import gql from 'graphql-tag';
import {Apollo} from 'apollo-angular';
import {Category} from '../../models/category.model';

const allCategories_Q = gql`
  query allCategories {
    allCategories {
      id
      name
      active
    }
  }
`;

const createCategory_M = gql`
  mutation createCategory($name: String!) {
    createCategory(name: $name) {
      id
      name
      active
    }
  }
`;

const toggleCategoryActive_M = gql`
  mutation toggleCategoryActive($id: ID!) {
    toggleCategoryActive(id: $id) {
      active
    }
  }
`;


@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss']
})
export class CategoryComponent implements OnInit {

  categoryName: String;
  categories: Category[];

  constructor(private apollo: Apollo) {
  }

  ngOnInit() {
    this.apollo.query({
      query: allCategories_Q
    }).subscribe((res) => {
      this.categories = res.data['allCategories'];
    }, (error) => {
      console.log(error);
    });
  }

  onCreateCategory() {
    this.apollo.mutate({
      mutation: createCategory_M,
      variables: {
        name: this.categoryName,
      }
    }).subscribe((res) => {
      this.categories = [...this.categories, res.data['createCategory']];
      this.categoryName = '';
    }, (error) => {
      console.log(error);
    });
  }

  onToggleActive(id: String, index: number) {
    this.apollo.mutate({
      mutation: toggleCategoryActive_M,
      variables: {
        id: id,
      }
    }).subscribe((res) => {
      this.categories[index].active = res.data['toggleCategoryActive'].active;
    }, (error) => {
      console.log(error);
    });
  }
}
