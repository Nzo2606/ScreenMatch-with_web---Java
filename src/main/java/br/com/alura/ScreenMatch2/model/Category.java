package br.com.alura.ScreenMatch2.model;

public enum Category {
    ACTION("Action", "Ação"),
    ROMANCE("Romance", "Romance"),
    COMEDY("Comedy", "Comédia"),
    DRAMA("Drama", "Drama"),
    CRIME("Crime", "Crime");

    private String omdbCategory;
    private String portugueseCategory;

    Category(String omdbCategory, String portugueseCategory){
        this.omdbCategory = omdbCategory;
        this.portugueseCategory = portugueseCategory;
    }

    public static Category fromString (String text){
        for (Category category : Category.values()){
            if (category.omdbCategory.equalsIgnoreCase(text)){
                return category;
            }
        }
        throw new IllegalArgumentException("No category found for the given string!");
    }

    public static Category fromPortuguese (String text){
        for (Category category : Category.values()){
            if (category.portugueseCategory.equalsIgnoreCase(text)){
                return category;
            }
        }
        throw new IllegalArgumentException("No category found for the given string!");
    }

}
