import controller.ConsolePostController;
import domain.Post;
import dto.PostReqDTO;
import view.ConsolePostView;

import java.util.List;
import java.util.Scanner;

public class ConsolePostApplication {
    private final ConsolePostView view;
    private final ConsolePostController controller;
    private final Scanner scanner = new Scanner(System.in);

    public ConsolePostApplication(ConsolePostView view, ConsolePostController controller) {
        this.view = view;
        this.controller = controller;
    }

    public void run() {
        boolean enable = true;
        while(enable) {
            view.menu();
            int menu = scanInt();
            switch (menu) {
                case 1:
                    List<Post> list = controller.findAllPost();
                    view.postListUp(list);
                    break;
                case 2:
                    view.inputId();
                    int readId = scanInt();
                    if (!validScanInt(readId)) break;
                    Post findPost = controller.findOne(readId);
                    view.printPost(findPost);
                    break;
                case 3:
                    view.inputTextAuthor();
                    String author = scanner.nextLine();
                    view.inputTextTitle();
                    String title = scanner.nextLine();
                    view.inputTextBody();
                    String body = scanner.nextLine();
                    PostReqDTO dto = new PostReqDTO(author, title, body);
                    Post saved = controller.createPost(dto);
                    view.printPost(saved);
                    break;
                case 4:
                    view.inputId();
                    int deleteId = scanInt();
                    if (!validScanInt(deleteId)) break;
                    controller.deleteOne(deleteId);
                    break;
                case 5:
                    enable = false;
                    break;
                default:
                    System.out.println("????????? ?????????????????? [1 ~ 5]");
                    break;
            }
        }
    }

    private int scanInt() {
        int input = -1;
        try {
            input = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ignore) {
        }
        return input;
    }

    private boolean validScanInt(int num) {
        if (num != -1) return true;
        System.out.println("???????????? ?????? ???????????????.");
        return false; // num??? ???????????? true, ???????????? ????????? false
    }
}
