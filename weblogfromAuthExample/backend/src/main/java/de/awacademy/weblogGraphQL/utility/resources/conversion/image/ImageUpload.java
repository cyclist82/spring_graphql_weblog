package de.awacademy.weblogGraphQL.utility.resources.conversion.image;

import de.awacademy.weblogGraphQL.utility.resources.FileUpload;
import de.awacademy.weblogGraphQL.utility.resources.ResourceDirectory;
import graphql.servlet.GraphQLContext;

import java.io.IOException;

public class ImageUpload extends FileUpload {

    public ImageUpload(GraphQLContext context, ResourceDirectory location, String name) {
        super(context, location, name);
    }

    @Override
    public void store() {
        if (getContext().getFiles().isPresent()) {
            try {
                setFile(ImageReformat.convert(getPart().getInputStream(), ImageType.PNG, ResourceDirectory.STORAGE_AVATAR, getName(), 256, 256));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
