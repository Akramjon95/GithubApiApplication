package maxcoders.uz.githubapplication.di

import dagger.Component
import maxcoders.uz.githubapplication.ViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [RetroModule::class])
interface RetroComponent {

    fun inject(viewModel: ViewModel)
}