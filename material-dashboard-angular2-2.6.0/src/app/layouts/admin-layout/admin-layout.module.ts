import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminLayoutRoutes } from './admin-layout.routing';
import { DashboardComponent } from '../../dashboard/dashboard.component';
import { UserProfileComponent } from '../../user-profile/user-profile.component';
import { TableListComponent } from '../../table-list/table-list.component';
import { TypographyComponent } from '../../typography/typography.component';
import { IconsComponent } from '../../icons/icons.component';
import { MapsComponent } from '../../maps/maps.component';
import { NotificationsComponent } from '../../notifications/notifications.component';
import { UpgradeComponent } from '../../upgrade/upgrade.component';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE, MatRippleModule} from '@angular/material/core';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatSelectModule} from '@angular/material/select';
import { ClientComponent } from 'app/page/rc/client/client.component';
import { MatTableModule } from '@angular/material/table';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { MatIconModule } from "@angular/material/icon";
import { MatCardModule } from '@angular/material/card';
import { ClientDlgComponent } from 'app/page/rc/client/client-dlg/client-dlg.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MomentDateAdapter } from '@angular/material-moment-adapter';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import * as moment from 'moment'
import { BranchComponent } from 'app/page/am/branch/branch.component';
import { BranchDlgComponent } from 'app/page/am/branch/branch-dlg/branch-dlg.component';
import { UserComponent } from 'app/page/am/user/user.component';
import { UserDlgComponent } from 'app/page/am/user/user-dlg/user-dlg.component';
import { UserViewDlgComponent } from 'app/page/am/user/user-view-dlg/user-view-dlg.component';
import { ClientViewDlgComponent } from 'app/page/rc/client/client-view-dlg/client-view-dlg.component';
import { MenuRolComponent } from 'app/page/am/menu-rol/menu-rol.component';
import { MatListModule } from '@angular/material/list';
import { MatExpansionModule } from '@angular/material/expansion';
import {MatAutocompleteModule} from '@angular/material/autocomplete'
import { EnteroNumber } from 'app/core/commons/pipes/entero-number.pipe';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,

    ReactiveFormsModule,
    MatButtonModule,
    MatRippleModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatTooltipModule,
    MatTableModule,
    NgxDatatableModule,
    MatIconModule,
    MatCardModule,
    MatDialogModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatListModule,
    MatExpansionModule,
    MatAutocompleteModule
  ],
  declarations: [
    DashboardComponent,
    UserProfileComponent,
    TableListComponent,
    TypographyComponent,
    IconsComponent,
    MapsComponent,
    NotificationsComponent,
    UpgradeComponent,
    ClientComponent,
    ClientDlgComponent,
    ClientViewDlgComponent,
    BranchComponent,
    BranchDlgComponent,
    UserComponent,
    UserDlgComponent,
    UserViewDlgComponent,
    MenuRolComponent,
    EnteroNumber
  ],
  providers: [
  ]
})

export class AdminLayoutModule {}

//moment.tz.setDefault('America/La_Paz');
