package by.it.group251052.hassan.lesson13;

import by.it.HomeWork;
import org.junit.Test;

@SuppressWarnings("NewClassNamingConvention")
public class Test_Part2_Lesson13 extends HomeWork {

    @Test
    public void testGraphA() {
        run("0 -> 1", true).include("0 1");
        run("0 -> 1, 1 -> 2", true).include("0 1 2");
        run("0 -> 2, 1 -> 2, 0 -> 1", true).include("0 1 2");
        run("0 -> 2, 1 -> 3, 2 -> 3, 0 -> 1", true).include("0 1 2 3");
        run("1 -> 3, 2 -> 3, 2 -> 3, 0 -> 1, 0 -> 2", true).include("0 1 2 3");
        run("0 -> 1, 0 -> 2, 0 -> 2, 1 -> 3, 1 -> 3, 2 -> 3", true).include("0 1 2 3");
        run("A -> B, A -> C, B -> D, C -> D", true).include("A B C D");
        run("A -> B, A -> C, B -> D, C -> D, A -> D", true).include("A B C D");
        //Дополните эти тесты СВОИМИ более сложными примерами и проверьте их работоспособность.
        //Параметр метода run - это ввод. Параметр метода include - это вывод.
        //Общее число примеров должно быть не менее 20 (сейчас их 8).

        run("A -> B, A -> C, B -> D, C -> D, D -> E, E -> F, F -> G", true).include("A B C D E F G"); // Complex graph
        run("1 -> 2, 1 -> 3, 2 -> 4, 3 -> 4, 4 -> 5, 5 -> 6, 5 -> 7, 6 -> 8, 7 -> 8, 8 -> 9, 9 -> 10", true).include("1 2 3 4 5 6 7 8 9 10"); // Long chain
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K, K -> L, L -> M, M -> N", true).include("A B C D E F G H I J K L M N"); // Long chain with letters
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K, K -> L, L -> M, M -> N", true).include("A B C D E F G H I J K L M N"); // Long chain with letters
        run("A -> B, A -> C, B -> D, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K, K -> L, L -> M, M -> N, N -> O, O -> P, P -> Q, Q -> A", true).include("A B C D E F G H I J K L M N O P Q"); // Complex graph with loop
        run("A -> B, A -> C, B -> D, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K, K -> L, L -> M, M -> N, N -> O, O -> P, P -> Q, Q -> R, R -> A", true).include("A B C D E F G H I J K L M N O P Q R"); // Complex graph with loop and additional edge
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K, K -> L, L -> M, M -> N, N -> O, O -> P, P -> Q, Q -> R, R -> S, S -> T, T -> A", true).include("A B C D E F G H I J K L M N O P Q R S T"); // Complex graph with loop
    }


    @Test
    public void testGraphB() {
        run("0 -> 1", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2, 2 -> 0", true).include("yes").exclude("no");
        //Дополните эти тесты СВОИМИ более сложными примерами и проверьте их работоспособность.
        //Параметр метода run - это ввод. Параметр метода include - это вывод.
        //Общее число примеров должно быть не менее 12 (сейчас их 3).

        run("0 -> 1, 1 -> 2, 2 -> 0, 2 -> 3, 3 -> 4, 4 -> 5, 5 -> 2", true).include("yes").exclude("no");
        run("0 -> 1, 1 -> 2, 2 -> 3, 3 -> 4, 4 -> 5, 5 -> 0", true).include("yes").exclude("no");
        run("A -> B, B -> C, C -> A", true).include("yes").exclude("no");
        run("0 -> 1, 0 -> 2, 1 -> 2", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2, 2 -> 3, 3 -> 4, 4 -> 5, 5 -> 6, 6 -> 7, 7 -> 8, 8 -> 9, 9 -> 0", true).include("yes").exclude("no");
        run("A -> B, A -> C, B -> D, C -> D, D -> E, E -> F, F -> G, G -> H, H -> A", true).include("yes").exclude("no");
        run("0 -> 1, 0 -> 2, 0 -> 3, 1 -> 4, 2 -> 4, 3 -> 4", true).include("no").exclude("yes");
        run("0 -> 1, 0 -> 2, 0 -> 3, 1 -> 4, 2 -> 4, 3 -> 4, 4 -> 0", true).include("yes").exclude("no");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K, K -> L, L -> M, M -> N, N -> O, O -> P, P -> Q, Q -> A", true).include("yes").exclude("no");
    }

    @Test
    public void testGraphC() {
        run("1->2, 2->3, 3->1, 3->4, 4->5, 5->6, 6->4", true)
                .include("123\n456");
        run("C->B, C->I, I->A, A->D, D->I, D->B, B->H, H->D, D->E, H->E, E->G, A->F, G->F, F->K, K->G", true)
                .include("C\nABDHI\nE\nFGK");
        //Дополните эти тесты СВОИМИ более сложными примерами и проверьте их работоспособность.
        //Параметр метода run - это ввод. Параметр метода include - это вывод.
        //Общее число примеров должно быть не менее 8 (сейчас их 2).
    }


}