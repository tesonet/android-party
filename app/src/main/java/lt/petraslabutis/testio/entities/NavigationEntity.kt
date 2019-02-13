package lt.petraslabutis.testio.entities

import lt.petraslabutis.testio.fragments.BaseFragment
import lt.petraslabutis.testio.viewmodels.NavigationViewModel

class NavigationEntity(
    val fragment: BaseFragment,
    val type: NavigationViewModel.Type
)