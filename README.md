# SocialHub
 A Social Network based on Spring

I used this blog to understand file download. File upload I had studied from chatgpt.
https://bushansirgur.in/spring-boot-file-upload-and-download-with-filesystem/

I modified this method a great deal by studying how facebook implements the image service. I pass the post id to which the image belongs to fetch the image instead of image name. Using auth, I can know which user is requesting. If the requester happens to be the owner of the image or a friend of the image, only then grant access. Otherwise return error code 401.