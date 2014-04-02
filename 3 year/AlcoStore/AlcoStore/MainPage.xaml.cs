namespace AlcoStore
{
    using System.Windows;
    using System.Windows.Controls;
    using System.Windows.Navigation;
    using AlcoStore.LoginUI;

    /// <summary>
    /// Класс <see cref="UserControl"/> реализует основную функциональность пользовательского интерфейса приложения.
    /// </summary>
    public partial class MainPage : UserControl
    {
        /// <summary>
        /// Создает новый экземпляр класса <see cref="MainPage"/>.
        /// </summary>
        public MainPage()
        {
            InitializeComponent();
            if (!WebContext.Current.User.IsAuthenticated)
                Link3.Visibility = Visibility.Collapsed;
            else
                Link3.Visibility = Visibility.Visible;
        }

        /// <summary>
        /// После перехода в рамке (Frame) проверьте, что ссылка <see cref="HyperlinkButton"/> представляет текущую выбранную страницу
        /// </summary>
        private void ContentFrame_Navigated(object sender, NavigationEventArgs e)
        {
            foreach (UIElement child in LinksStackPanel.Children)
            {
                HyperlinkButton hb = child as HyperlinkButton;
                if (hb != null && hb.NavigateUri != null)
                {
                    if (hb.NavigateUri.ToString().Equals(e.Uri.ToString()))
                    {
                        VisualStateManager.GoToState(hb, "ActiveLink", true);
                    }
                    else
                    {
                        VisualStateManager.GoToState(hb, "InactiveLink", true);
                    }
                }
                WebContext.Current.Authentication.LoggedIn += new System.EventHandler<System.ServiceModel.DomainServices.Client.ApplicationServices.AuthenticationEventArgs>(Authentication_LoggedIn);
                WebContext.Current.Authentication.LoggedOut += new System.EventHandler<System.ServiceModel.DomainServices.Client.ApplicationServices.AuthenticationEventArgs>(Authentication_LoggedOut);
            }
        }

        /// <summary>
        /// Если при переходе возникла ошибка, отобразить окно сообщения
        /// </summary>
        private void ContentFrame_NavigationFailed(object sender, NavigationFailedEventArgs e)
        {
            e.Handled = true;
            ErrorWindow.CreateNew(e.Exception);
        }
        void Authentication_LoggedOut(object sender, System.ServiceModel.DomainServices.Client.ApplicationServices.AuthenticationEventArgs e)
        {
            Link3.Visibility = Visibility.Collapsed;
        }

        void Authentication_LoggedIn(object sender, System.ServiceModel.DomainServices.Client.ApplicationServices.AuthenticationEventArgs e)
        {
            Link3.Visibility = Visibility.Visible;
        }
    }
}