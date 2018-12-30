package com.tunabaranurut.budgetmanager_android.model.route;

import com.tunabaranurut.budgetmanager_android.model.Category;
import com.tunabaranurut.budgetmanager_android.model.Response;

public class GetCategoryResponse {

    private Category category;

    private Response response;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
