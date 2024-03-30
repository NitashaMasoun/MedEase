package com.admin.medease.clickinterface

import com.admin.medease.model.SpecialisationModel

interface ClickInterface {
    fun onClick(specialisationModel: SpecialisationModel)
    fun onDelete(specialisationModel: SpecialisationModel)
    fun onNextClick(specialisationModel: SpecialisationModel)

}