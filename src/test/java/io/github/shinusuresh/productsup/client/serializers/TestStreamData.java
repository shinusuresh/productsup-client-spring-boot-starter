package io.github.shinusuresh.productsup.client.serializers;

import io.github.shinusuresh.productsup.client.data.BaseStreamData;

public class TestStreamData extends BaseStreamData {

    private String name;
    private String price;

    public TestStreamData(String id, String name, String price) {
        super(id);
        this.name = name;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
