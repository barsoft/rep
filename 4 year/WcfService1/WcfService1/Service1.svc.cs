using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace WcfService1
{
    // ПРИМЕЧАНИЕ. Команду "Переименовать" в меню "Рефакторинг" можно использовать для одновременного изменения имени класса "Service1" в коде, SVC-файле и файле конфигурации.
    // ПРИМЕЧАНИЕ. Чтобы запустить клиент проверки WCF для тестирования службы, выберите элементы Service1.svc или Service1.svc.cs в обозревателе решений и начните отладку.
    public class Service1 : IService1
    {
        public string GetData(int value)
        {
            return string.Format("You entered: {0}", value);
        }

        public CompositeType GetDataUsingDataContract(CompositeType composite)
        {
            if (composite == null)
            {
                throw new ArgumentNullException("composite");
            }
            if (composite.BoolValue)
            {
                composite.StringValue += "Suffix";
            }
            return composite;
        }

        public DateTime GetTime()
        {
            return DateTime.Now;
        }

        public List<DriveInfo> LoadDrives()
        {
            var drives = new List<DriveInfo>();
            foreach (DriveInfo drive in DriveInfo.GetDrives())
            {
                if (drive.IsReady)
                {
                    drives.Add(drive);
                }
            }
            return drives;
        }

        public string[] GetCPUCoresInfo()
        {
            List<string> infos = new List<string>();
            var pc = new PerformanceCounter("Processor Information", "% Processor Time");
            var cat = new PerformanceCounterCategory("Processor Information");
            var instances = cat.GetInstanceNames();
            var cs = new Dictionary<string, CounterSample>();

            foreach (var s in instances)
            {
                pc.InstanceName = s;
                cs.Add(s, pc.NextSample());
            }

            foreach (var s in instances)
            {
                pc.InstanceName = s;
                infos.Add(s + "  -  " + Calculate(cs[s], pc.NextSample()));
                cs[s] = pc.NextSample();
            }

            return infos.ToArray();
        }

        public static Double Calculate(CounterSample oldSample, CounterSample newSample)
        {
            double difference = newSample.RawValue - oldSample.RawValue;
            double timeInterval = newSample.TimeStamp100nSec - oldSample.TimeStamp100nSec;
            if (timeInterval != 0) return 100 * (1 - (difference / timeInterval));
            return 0;
        }
    }
}
