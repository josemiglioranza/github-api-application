package com.ze.githubrepository.ui
import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.ze.githubrepository.R
import com.ze.githubrepository.model.Github
import com.ze.githubrepository.model.Person
import org.junit.Test

class GithubRepositoryViewTest {

    private val githubRepositoryGui = mockGithubRepository(
        "Repositorio do Gui",
        "Repositório de SP com Java",
        1000,
        2000,
        Person ("caminho da foto do Gui", "fofo Gui")
    )

    private val githubRepositoryCa = mockGithubRepository(
        "Repositorio da Ca",
        "Repositório de Osasco com Java",
        3000,
        4000,
        Person ("caminho da foto da Ca", "fofa Ca")
    )

    private val githubRepositoryLuquinhas = mockGithubRepository(
        "Repositorio do Luquinhas",
        "Repositório de São José com Java",
        5000,
        6000,
        Person ("caminho da foto do Luquinhas", "fofo Luquinhas")
    )

    private val githubRepositoryOsias = mockGithubRepository(
        "Repositorio do Osias",
        "Repositório da Bahia com Java",
        7000,
        8000,
        Person ("caminho da foto do Osias", "fofo Osias")
    )


    @Test
    fun showFilledCustomView_WithMockedGithubForGui(){
        // given - setando tudo o que for necessário para o teste funcionar

        /*
        customView - Pegamos o GithubRepositoryView (classe da nossa custom view) e como ela
        precisa de um contexto utilizamos essa classe IntrumentationRegistry que consegue nos
        fornecer uma referência a instrumentação em execução, para acessar o método getInstrumentation()
        e assim conseguir capturar o contexto da nossa classe GithubRepositoryView.
         */
        val customView = GithubRepositoryView(InstrumentationRegistry.getInstrumentation().targetContext)

        /*
        context - Dessa vez não estamos mirando em uma classe específica, é como se fosse uma variável genérica
        que quando atrelarmos a alguma classe ela vai capturar o contexto dela, inclusive podemos passar essa variável
        como parâmetro do customView
         */
//        val context = InstrumentationRegistry.getInstrumentation().targetContext

        // when
        /*
        Luquinhas comentou que sempre que vamos testar uma customView, precisamos de uma activity/fragment
        para ser o receptor dessa view, pois uma View precisar estar atrelada a um layout para funcionar
        e aqui nesse teste temos que seguir isso também, nada vai funcionar por mágica
         */

        customView.launch()
        customView.setup(githubRepositoryGui)

        //then
        onView(withId(R.id.txt_name_repository)).check(matches(withText("Repositorio do Gui")))
        onView(withId(R.id.txt_repository_description)).check(matches(withText("Repositório de SP com Java")))
        onView(withId(R.id.fork_text)).check(matches(withText(1000.toString())))
        onView(withId(R.id.star_text)).check(matches(withText(2000.toString())))
    }
}

fun mockGithubRepository(
    name: String,
    description: String,
    forks: Long,
    stars: Long,
    owner: Person
) = Github (name, forks, stars, owner, description)

fun View.launch(){
    ActivityScenario.launch(MainActivity::class.java).onActivity { activity ->
        activity.setContentView(this)
    }
}