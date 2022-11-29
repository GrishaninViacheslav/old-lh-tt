package io.github.grishaninvyacheslav.lh.company_details.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.IconStyle
import com.yandex.runtime.image.ImageProvider
import io.github.grishaninvyacheslav.lh.company_details.R
import io.github.grishaninvyacheslav.lh.company_details.data.entity.CompanyDetailsEntity
import io.github.grishaninvyacheslav.lh.company_details.databinding.FragmentCompanyDetailsBinding
import io.github.grishaninvyacheslav.lh.core_ui.presentation.BaseFragment
import io.github.grishaninvyacheslav.lh.network.BuildConfig
import org.koin.androidx.viewmodel.ext.android.viewModel

class CompanyDetailsFragment :
    BaseFragment<FragmentCompanyDetailsBinding>(FragmentCompanyDetailsBinding::inflate) {
    companion object {
        val COMPANY_ID_ARG = "COMPANY_ID"
        fun newInstance(companyId: String) = CompanyDetailsFragment().apply {
            arguments = Bundle().apply { putString(COMPANY_ID_ARG, companyId) }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initView()
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        binding.companyLocationPreview.onStart()
    }

    override fun onStop() {
        binding.companyLocationPreview.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    private fun initObservers() = requireArguments().getString(COMPANY_ID_ARG)?.let { companyId ->
        viewModel.getCompaniesListState(companyId)
            .observe(viewLifecycleOwner) { renderCompanyDetailsState(it) }
    }

    private fun initView() = with(binding) {
        retryAction.setOnClickListener {
            requireArguments().getString(COMPANY_ID_ARG)?.let {
                viewModel.updateCompanyDetailsState(it)
            }
        }
    }

    private fun renderCompanyDetailsState(state: CompanyDetailsState) = when (state) {
        CompanyDetailsState.Loading -> {
            setProgressBarStatus(true)
            setRetryError(null)
        }
        is CompanyDetailsState.Success -> {
            setProgressBarStatus(false)
            setRetryError(null)
            initCompanyDetails(state.companyDetails)

        }
        is CompanyDetailsState.Error -> {
            setProgressBarStatus(false)
            setRetryError(state.error.message)
            showErrorMessage(state.error)
        }
    }

    private fun setProgressBarStatus(isInProgress: Boolean) = with(binding) {
        progressBar.isVisible = isInProgress
    }

    private fun setRetryError(errorMessage: String?) = with(binding) {
        errorMessage?.let {
            companyDetailsBody.isVisible = false
            retry.isVisible = true
            retryErrorMessage.text = it
        } ?: run {
            companyDetailsBody.isVisible = true
            retry.isVisible = false
            retryErrorMessage.text = ""
        }
    }

    private fun initCompanyDetails(companyDetails: CompanyDetailsEntity) = with(binding) {
        initLocationPreview(companyDetails.lat, companyDetails.lon)
        initCompanyImage(companyDetails.img)
        initCompanyContacts(companyDetails.www, companyDetails.phone)
        initCompanyDescription(companyDetails.name, companyDetails.description)
    }

    private fun initLocationPreview(lat: Double, lon: Double) = with(binding.companyLocationPreview) {
        if(lat == 0.0 && lon == 0.0){
            isVisible = false
            return
        }

        binding.companyLocationPreviewBlock.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("geo:$lat,$lon?z=12")
            })
        }

        map.mapObjects.addPlacemark(Point(lat, lon),
            ImageProvider.fromResource(requireContext(), R.drawable.ic_location),
            IconStyle().apply {
                scale = 0.05F
            })

        map.move(
            CameraPosition(
                Point(
                    lat, lon
                ), 12.0f, 0.0f, 0.0f
            )
        )
    }

    private fun initCompanyImage(url: String) =
        Glide
            .with(binding.companyImage)
            .load(BuildConfig.API_URL + url)
            .placeholder(R.drawable.ic_no_image)
            .centerCrop()
            .into(binding.companyImage)


    private fun initCompanyContacts(www: String, phone: String) = with(binding) {
        if (www.isBlank() && phone.isBlank()) {
            companyContactsCard.isVisible = false
            return
        }
        if(www.isNotBlank()){
            companySite.text = www
            companySite.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW).apply { data = Uri.parse("http://$www") })
            }
        } else {
            companySite.isVisible = false
        }
        if(phone.isNotBlank()){
            companyPhone.text = phone
            companyPhone.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("tel:$phone")))
            }
        } else {
            companyPhone.isVisible = false
        }
    }

    private fun initCompanyDescription(name: String, description: String) = with(binding) {
        if (description.isBlank()) {
            companyDescriptionCard.isVisible = false
        }
        companyName.text = name
        if(description.isNotBlank()){
            companyDescription.text = description
        } else {
            companyDescription.isVisible = false
        }
    }

    private val viewModel: CompanyDetailsViewModel by viewModel()
}

